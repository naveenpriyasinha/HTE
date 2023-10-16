<%@ include file="../core/include.jsp" %>
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst,java.util.List,java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject,java.util.Map,com.tcs.sgv.billproc.cheque.valueobject.ChequeVO"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle bundle = ResourceBundle.getBundle("resources/billproc/BillprocConstants");
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
		<script type="text/javascript" src="script/billproc/validation.js"></script>
		<script>
		
		function searching()
		{
			var str= document.frmChqPrinting.txtSearch.value;
			if(str=='')
			{
			 	alert('Search Text is Empty');
			 	return;
			}
			if(document.frmChqPrinting.cmbSearch.value =='fromDt')
				{
					if(!isDate(document.frmChqPrinting.txtSearch.value))
					{
						return;
					}
				}
			
				document.frmChqPrinting.action='ifms.htm?actionFlag=getCheques&StatusFlag='+'<%=bundle.getString("STATUS.CheqWritten")%>';
			
			
			document.frmChqPrinting.displ.value=str;			
			document.frmChqPrinting.method='post';
			document.frmChqPrinting.submit();

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
	     	
	     	var startChq=document.frmChqPrinting.nextCheque.value;
	     	if(document.frmChqPrinting.chkSelect.checked == true)
	     	{
	    		var incNo=0;
				for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          document.getElementById(incNo).value='';
			          document.getElementById(incNo).value=startChq++;
			          incNo++;	
			        } 
			    } 
			 }
			 else
			 {
			 	var incNo=0;
			 	for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {
			 			document.getElementById(incNo).value='';
			 			incNo++;
			 		}
			 	}	
			 }   
	     }
		
			function generatePDF()
			{
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {

			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert(' Select atleast one checkbox ');
			       return false;
			    }				
				url ="ifms.htm?actionFlag=generateChequePDF&Cheques="+arr;
				window.open(url,'_blank','');
			}
			
			function printCheque()
			{
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {

			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
			            indx = indx+1;
			            flag =1;
			          }
			        }
			    }
			    if(flag ==0)
			    {
			       alert(' Select atleast one checkbox ');
			       return false;
			    }
			    var nextCheq = document.frmChqPrinting.nextCheque.value;
			    if(nextCheq=='' || nextCheq =='0' )
			    {
			    	alert('Starting Cheque No. is not valid');
			    	document.frmChqPrinting.nextCheque.focus();
			    	return false;
			    }
			    document.frmChqPrinting.selectCheques.value=arr;
			    //insertdt();			     
			    document.frmChqPrinting.btnPrintChqs.disabled = true;
			    document.frmChqPrinting.action ="ifms.htm?StatusFlag="+'<%=bundle.getString("STATUS.CheqWritten")%>';
			    document.frmChqPrinting.submit();
			}
			function showDtPic()
  			{
  				if(document.frmChqPrinting.cmbSearch.value=="fromDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			function returnBill()
			{
				document.frmChqPrinting.actionFlag.value="sendToCounter";
				var arr = new Array();
			    var indx  =0;
			    var flag =0;
			    for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			            arr[indx]  = document.frmChqPrinting.elements[i].value;
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
				if(confirm('Existing cheques will be cancelled.Do you still want to proceed ?'))
				{
					document.frmChqPrinting.action = 'ifms.htm?actionFlag=sendToCounter&ReturnBill=Yes&StatusFlag='+'<%=bundle.getString("STATUS.CheqWritten")%>';
					document.frmChqPrinting.submit();
				}
			}
			function onSearch(e)
  			{
  				
  				if(e.keyCode==13)
  				{
  					var str= document.frmChqPrinting.txtSearch.value;
					if(str=='')
					{
						//document.rm_accvousfrom.parentUrl1.value = "";
						alert("Search Text is Empty");
						return false;
					}
					else
					{
  						searching();
  					}
  				}
  			}
			function changChqNo()
			{
				var startChq=document.frmChqPrinting.nextCheque.value;
				var incNo=0;
				for(i=0;i<document.frmChqPrinting.elements.length;i++)
			    {
			        if(document.frmChqPrinting.elements[i].type=="checkbox" && document.frmChqPrinting.elements[i].name !='chkSelect')
			        {	
			          document.getElementById(incNo).value='';
			          if(document.frmChqPrinting.elements[i].checked == true)
			          {
			          		document.getElementById(incNo).value=startChq++;
			          }
			          incNo++;	
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
		
		<%
			Map map = (Map)((ResultObject)request.getAttribute("result")).getResultValue();
			List list = (List)map.get("ChequeList");
			for(int i=0;i< list.size();i++)
			{
				ChequeVO cheq= (ChequeVO)list.get(i);
			}
			
			%>
		<hdiits:form name="frmChqPrinting" validate="true" method="POST">
		<input type="hidden"  name="displ" value="" />
		
		<input type="hidden" name="actionFlag" id="actionFlag" value="printCheques">
		<input type="hidden"  name="selectCheques" />
    	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
		<div id="progressImage" style="display:none"></div>		
		<table width="100%" align="center">
			<tr>
				<td>
					<table width="100%" align="center">						
						<tr><td><br></td></tr>
						<tr align="center" class="TableHeaderBG">
							<td class="datatableheader">
								<b><fmt:message key="CHQPRINTING.TITLE" bundle="${billprocLabels}"></fmt:message></b>
							</td>
						</tr>
						<tr><td><br></td></tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>	
						<table align="center" width="90%" bordercolor="BLACK">							
							<tr>
								<td width="52%">
								</td>
								<td width="8%" align="left">
									<fmt:message key="CHQCOMMON.SEARCH" bundle="${billprocLabels}"></fmt:message> :
								</td>
								<td align="left">
									<input type="text" name="txtSearch" size="15"  onkeypress="javascript:onSearch(event);" value="<% if(request.getParameter("txtSearch")!=null ){  out.print(request.getParameter("txtSearch")); } %>">
									<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif"   width=20 onClick=window_open("txtSearch",375,570) ></div>
								</td>
								<td width="*">
									<select name="cmbSearch" onchange="showDtPic()">
										<hdiits:option value="0">-----Select-----</hdiits:option>
										<option value="billCntrlNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("billCntrlNo") ) {  out.print("selected"); }  %> >
											<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
										</option>
										<option value="partyName"  <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("partyName") ) {  out.print("selected"); }  %> >
											<fmt:message key="CMN.PARTY_NAME" bundle="${billprocLabels}"  ></fmt:message>
										</option>
										<option value="fromDt" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("fromDt") ) {  out.print("selected"); }  %>  >
											<fmt:message key="CHQ.CHEQUE_DATE" bundle="${billprocLabels}"></fmt:message>
										</option>
										<option value="tokSrc" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("tokSrc") ) {  out.print("selected"); }  %> >
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
				<tr>
						<td align="left" colspan="3">
							<fmt:message key="CHQPRINTING.STARTNO" bundle="${billprocLabels}"></fmt:message> : <hdiits:text name="nextCheque" onblur="changChqNo()" default="${resValue.LatestChequeNumber}" ></hdiits:text>						
						</td>
				</tr>
				<tr><td colspan="3"></td></tr>
				
   		<tr>
   			<td>
   						<%
							int i=0;
							int j=0;
						%>
	   			<display:table list="${cheqList}" pagesize="25" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   	id="VO" excludedParams="ajax" varTotals="totals"  partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre"  title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" ><div align="center"><input name="chkbox" value="${VO.trnChequeDtls.chequeId}~${VO.trnChequeDtls.groupId}" type="checkbox" onclick="changChqNo()"  /></div>
					
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
						 <display:column class="oddcentre" title="Cheque Number"  headerClass="datatableheader" style="text-align:center"><input type="text"  name="chqNumbers" id="<%=j%>" size="10" readonly="readonly"></display:column>	    
					<display:column class="oddcentre" titleKey="CMN.BILL_CONTROL_NO" sortable="true" headerClass="datatableheader" style="text-align:center" >
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
					<display:column class="oddcentre" titleKey="CMN.TOKEN_NO" sortable="true" headerClass="datatableheader" style="text-align:center" >
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
					<display:column property="trnChequeDtls.partyName" class="oddcentre" titleKey="CMN.PARTY_NAME" sortable="true" headerClass="datatableheader"/>
					<display:column property="trnChequeDtls.chequeAmt" class="oddcentre" titleKey="CMN.CHEQUE_AMOUNT" sortable="true" headerClass="datatableheader" style="text-align:center"></display:column>
					<display:column property="trnChequeDtls.fromDt" class="oddcentre" format="{0,date,dd/MM/yyyy}" titleKey="CHQ.CHEQUE_DATE" sortable="true" headerClass="datatableheader"  style="text-align:center"/>
 					<display:column property="writerName" class="oddcentre" titleKey="CHQ.WRITER_NAME" sortable="true" headerClass="datatableheader" />
	  				<display:footer media="html">
					</display:footer>
					<%
						j++;
					%>
		  	</display:table> 
	</td></tr>
	   	<tr>
   			<td align="center">
				<center>
				   	<hdiits:button type="button" name="btnReturn" value='<%=buttonBundle.getString("COMMON.RETURN")%>'  onclick="returnBill()"></hdiits:button>&nbsp;  
		   			<hdiits:button type="button" classcss="bigbutton" name="btnPDFChqs" value='<%=buttonBundle.getString("CHQPRINTING.CHQPDF")%>'  onclick="generatePDF()"></hdiits:button>
 		   			<hdiits:button type="button" classcss="bigbutton" name="btnPrintChqs" value='<%=buttonBundle.getString("CHQPRINTING.CHQPRINT")%>' onclick="printCheque()"></hdiits:button>
	   			</center>
   			</td>
	   	</tr>
	   </table>
	   <script>
	  	if(document.frmChqPrinting.txtSearch.value!=null && document.frmChqPrinting.cmbSearch.value=="fromDt")
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
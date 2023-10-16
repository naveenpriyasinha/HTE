<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>


<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.FrmServiceMst"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnBillRegister"%>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/billproc/validation.js"></script>
<script type="text/javascript" src="script/billproc/cmnValidation.js"></script>
<script type="text/javascript" src="script/billproc/billMvmntTracking.js"></script>
<script type="text/javascript"><!--
  	 
	function submitData()
	{
			document.rm_accvousfrom.parentUrl1.value='';
			//alert('in submit'+document.forms[0]);
			document.rm_accvousfrom.action="ifms.htm?actionFlag=updateAcceptBills&page=savedBillsUpdate";
			document.rm_accvousfrom.submit();	
	}
			
	function displ()
			{
			document.rm_accvousfrom.parentUrl1.value='';
			
			if(peer=='n')
			{
				//document.rm_accvousfrom.action="ifms.htm?actionFlag=receiveOnlineBill&recFlag=1&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag=BCRDX";
				document.rm_accvousfrom.action="ifms.htm?actionFlag=receiveOnlineBill&recFlag=0&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag=BAUD";	
			}
			else
			{
				document.rm_accvousfrom.action="ifms.htm?actionFlag=receiveOnlineBill&recFlag=0&statusFlag="+'<%=request.getParameter("statusFlag")%>'+"&updStatusFlag=BSNT_TO";	
			}
			document.rm_accvousfrom.submit();	
			}	
			
  			
  			
  			
  	
--></script>
<%int i=1;  System.out.println(" In Jsp ::: " + i); %>
      <c:set var="resultObj" value="${result}" > </c:set>
	  <c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
	  <c:set var="billList" value="${resValue.billList}"> </c:set>
	  <c:set var="validFlag" value="${resValue.validFlag}"> </c:set>
	  <c:set var="inValidList" value="${resValue.inValidList}"> </c:set>
	  <%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>
	  
<hdiits:form name="rm_accvousfrom" validate="true" method="post"  >
			<input type="hidden" name="toPost" value="-1">
			<input type="hidden" name="toUser" value="-1">
			<input type="hidden" name="SendTo" value="-1">
			<input type="hidden" name="toLevel" value="-1">
			
			
			
			<%
				String queryString= (String)request.getQueryString();
				if(queryString != null)
				{
					queryString = queryString.replaceAll("&","*");
					System.out.println("----------------------  " + queryString);
				}
			%>		
			
			<input type="hidden" name="parentUrl1" value="ifms.htm?<%=queryString%>" >	
			
			<input type="hidden" name="recFlag" value="<%=request.getParameter("recFlag").toString()%>" >
			
			<%--  <input type="hidden" name="parentUrl" value="<%=request.getContextPath()%>/ifms.htm?<%=request.getQueryString()%>"> --%>
	  <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
	  	<tr class="TableHeaderBG"> 
			<td  align="center">
			<b><fmt:message key="COUNTERONLINEBILL.TITLE" bundle="${billprocLabels}"></fmt:message></b>
			</td>
		</tr>
		<tr> 
			<td  align="left">&nbsp;  <c:out value="${inValidList}"/> </td>
		</tr>
		<tr>
			<table align="center" width="90%" align="left">
					<tr>
						<td width="53%">
						</td>
						<td width="8%" align="left">
							<fmt:message key="CHQCOMMON.SEARCH" bundle="${billprocLabels}"></fmt:message> :
						</td>
						<td >
							<input type="text" name="txtSearch" onkeypress="javascript:onSearch(event,'<%=request.getParameter("statusFlag")%>','<%=request.getParameter("recFlag")%>','<%=request.getParameter("updStatusFlag")%>');" size="15" value="<% if(request.getParameter("txtSearch")!= null) { out.print(request.getParameter("txtSearch")); }  %>" >
							<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif"   width="20" onClick=window_open("txtSearch",375,570) ></div>
						</td>
					<td width="*">
							<select name="cmbSearch" id="id_cmbSearch" onchange="showDtPic()">
							<option value="0">-----Select-----</option>
							
							<option value="p.billCntrlNo" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.billCntrlNo")) { out.print("selected"); }  %> >
								<fmt:message key="CMN.BILL_CONTROL_NO" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="p.tokenNum" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.tokenNum")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.TOKEN_NO" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="mb.subjectDesc" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("mb.subjectDesc")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_TYPE" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="p.inwardDt" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.inwardDt")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_DATE" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="odm.ddoNo" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("odm.ddoNo")) { out.print("selected"); }  %> >
								<fmt:message key="CNTR.DDO_NO" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="odm.ddoName" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("odm.ddoName")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.DDO_NAME" bundle="${billprocLabels}"></fmt:message>
							</option>
							<option value="p.budmjrHd" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("p.budmjrHd")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.MAJOR_HEAD" bundle="${billprocLabels}"></fmt:message>
							</option>
							
							<option value="clm.lookupName" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("clm.lookupName")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILL_CATEGORY" bundle="${billprocLabels}"></fmt:message>
							</option>
						</select>
					</td>
					<td width="2%">
						<a href="#" onclick="javascript:searching('<%=request.getParameter("statusFlag")%>','<%=request.getParameter("recFlag")%>','<%=request.getParameter("updStatusFlag")%>');"><img src="common/images/search.gif" align="right" height="16" width="16"  /></a>
					</td>
				</tr>
			</table>	
			</tr>
		<tr> 
			<td  align="center">&nbsp;</td>
		</tr>
		</table>
	  	<%int j=0; %>
	  	<display:table list="${billList}" pagesize="25" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   id="VO" excludedParams="ajax" varTotals="totals"  partialList="" style="width:100%">
					<display:setProperty name="paging.banner.placement" value="bottom"/>    
					<display:column class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" ><input name="chkbox" value="${VO.billNo}" type="checkbox" > </display:column>	    
					
					<display:column class="oddcentre" titleKey="CMN.BILL_CONTROL_NO" sortable="true" headerClass="datatableheader" style="width:23%">
					<c:choose>
						<c:when test="${VO.phyBill==1}">
							<c:set var="URLLink" value="ifms.htm?actionFlag=showBill&BillNo=${VO.billNo}"  ></c:set>											
						</c:when>
						<c:otherwise>
							<c:set var="URLLink" value="ifms.htm?actionFlag=getBillData&billNo=${VO.billNo}&billStatus=BSNT_TO"  ></c:set>
						</c:otherwise>
					</c:choose>
					
							<a href="javascript:void(0)" onclick="javascript:showBills('${VO.billNo}','<%=request.getParameter("statusFlag")%>')">
								<c:out value="${VO.billCntrlNo}"/>
							</a>
					</display:column>
					<display:column  class="oddcentre" titleKey="CMN.TOKEN_NO" headerClass="datatableheader" >
					<input type="text" name="tokens_<c:out value="${VO.billNo}"/>" id="tokens_<c:out value="${VO.billNo}"/>" size="4" >
					</display:column>
					<display:column property="billGrossAmount" class="oddcentre" titleKey="CMN.BILL_AMOUNT" sortable="true" headerClass="datatableheader"  />
					<display:column property="subjectDesc" class="oddcentre" titleKey="CMN.BILL_TYPE" sortable="true" headerClass="datatableheader" style="width:20%"  />
					<display:column property="inwardDt" class="oddcentre" format="{0,date,dd/MM/yyyy}" titleKey="CMN.BILL_DATE" sortable="true" headerClass="datatableheader"  />
				    <display:column property="ddoNO" class="oddcentre" titleKey='CNTR.DDO_NO' sortable="true" headerClass="datatableheader"  />
				    <display:column  class="oddcentre" titleKey="CMN.DDO_NAME_CARDEX" sortable="true" headerClass="datatableheader" style="width:20%" >
				    <c:out value="${VO.ddoName}"/>[<c:out value="${VO.cardexNo}"/>]
				    </display:column>
				    <display:column property="budmjrHd" class="oddcentre" titleKey="CMN.MAJOR_HEAD" sortable="true" headerClass="datatableheader"  />				
				    <display:column class="oddcentre" titleKey="CMN.BILL_CATEGORY" headerClass="datatableheader" sortable="true">
			   	    		<c:out value="${VO.lookupName}"/>
	   					</display:column>
				    <display:column class="oddcentre" titleKey="CMN.AUDITOR_NAME" headerClass="datatableheader">
				    		<select name="cmb_<c:out value="${VO.billNo}"/>" id="cmb+<%=j%>">
	   								<option value="-1">-----Select-----</option>
	   								<c:forEach var="emp" items="${VO.audList}" varStatus="counter">
		   								<option  value="<c:out value="${emp.postId}"/>"><c:out value="${emp.fullName}"/></option>   									   									
	   								</c:forEach>
	 									<c:set var="empPostVO" value="${VO.empPostVO}" />
	 									<c:out value="${VO.empPostVO.postId}"/>	 									
	 								<c:choose>
   										<c:when test="${VO.audPostId == '-1'}"  >
	   										<c:if test="${VO.empPostVO.postId!=null}" >
			   									<script type="text/javascript">
			   										document.getElementById('cmb+<%=j%>').value='<c:out value="${empPostVO.postId}"/>';	
			   									</script>
		   								    </c:if>
   										</c:when>					
   										<c:otherwise>
											<script type="text/javascript">
	   											document.getElementById('cmb+<%=j%>').value='<c:out value="${VO.audPostId}"/>';	
	   										</script>	   							
	   									</c:otherwise>
	   							     </c:choose>
	 							</select>	
			   	    	</display:column>
			   	    					
			   	    	<%j++; %>
				    <display:footer media="html">
				  </display:footer>
			
	  </display:table>  
	  <input type="hidden" name="records" value="<%=j%>">
	 <table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">

		
	  	<tr>
					   		<td align="center" colspan="10">
					   		<input class="buttontag"  type="button" name="Receive" value='<%=buttonBundle.getString("COMMON.RECEIVE")%>' onclick="receiveOnline('<%=request.getParameter("statusFlag")%>');">
					   		<input class="buttontag" type="button" name="btnPeer" value='<%=buttonBundle.getString("COMMON.SENDTOPEER")%>' onclick="peerOnline('Peer','<%=request.getParameter("updStatusFlag")%>');" >
					   		<input class="buttontag" type="button" name="Test" value='<%=buttonBundle.getString("COMMON.FORWARD")%>' onclick="fwdOnline('<%=request.getParameter("statusFlag")%>');" >
							
					   		</td>
					   	</tr> 
	  	</table>
	  	<script>
	  	if(document.rm_accvousfrom.txtSearch.value!=null && document.rm_accvousfrom.cmbSearch.value=="p.inwardDt")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
	  	</script>
	  	<c:if test="${validFlag=='1' }">
			<script>
				alert("Not Valid Token");
			</script>
			</c:if> 
</hdiits:form>


<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    

<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import="java.util.Map"%>

<fmt:setLocale value='<%=(String)session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.onlinebillprep.CommonLabels" var="onlinebillcmnLabels" scope="application"/>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillcmnAlerts" scope="application"/>

<head>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/hrms/onlinebillprep/Common.js"></script>
<script type="text/javascript">
function window_new_open()
		{
			var newWindow = null;
	    	var x = 150;
	    	var y = 150;
	    	var urlstring = "ifms.htm?viewName=ddoHeadDtlsPopup";
	    	var urlstyle = "height=300,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
	    	newWindow = window.open(urlstring, "savedBillsForm", urlstyle);
		}
</script>
	
	
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		 	 
	<script type="text/javascript">
		
		var SVBILL_SELECTBILL = "<fmt:message key='SVBILL.SELECTBILL' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SVBILL_ONEBILLFORWARDING = "<fmt:message key='SVBILL.ONEBILLFORWARDING' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SVBILL_BILLNOTAPPROVED = "<fmt:message key='SVBILL.BILLNOTAPPROVED' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SVBILL_SEARCHFILDISEMPTY = "<fmt:message key='SVBILL.SEARCHFILDISEMPTY' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SVBILL_SRCHCRITERIANOTSELECTED = "<fmt:message key='SVBILL.SRCHCRITERIANOTSELECTED' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SVBILL_RJCTDBILLCRTD = "<fmt:message key='SVBILL.RJCTDBILLCRTD' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		
		var minYear=1900;
		var maxYear=2500;
		var SRCH_DTFORMAT = "<fmt:message key='SRCH.DTFORMAT' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SRCH_VALMNTH = "<fmt:message key='SRCH.VALMNTH' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SRCH_VALDAY = "<fmt:message key='SRCH.VALDAY' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		var SRCH_VALDIGIT = "<fmt:message key='SRCH.VALDIGIT' bundle='${onlinebillcmnAlerts}'></fmt:message>"+ ' ' + minYear+ ' ' + "<fmt:message key='SRCH.AND' bundle='${onlinebillcmnAlerts}'></fmt:message>"+ ' ' +maxYear;
		var SRCH_VALDT = "<fmt:message key='SRCH.VALDT' bundle='${onlinebillcmnAlerts}'></fmt:message>";
		
   		function searching()/// Added By Sagar For Searching
		{
		var str= document.savedBillsForm.txtSearch.value.trim();

		if(str=='')
		{
			alert(SVBILL_SEARCHFILDISEMPTY);
			return;
		}
		if(document.savedBillsForm.cmbSearch.value == '0')
		{
			alert(SVBILL_SRCHCRITERIANOTSELECTED);
			return;
		}
		
		if(document.savedBillsForm.cmbSearch.value =='BR.billDate')
		{
			if(!isDate(document.savedBillsForm.txtSearch.value))
			{
				return;
			}
		}
						
		//document.savedBillsForm.parentUrl1.value = "";
		document.savedBillsForm.action='ifms.htm?actionFlag=getMySavedBills&billStatus=saved&statusFlag='+str+'&recFlag='+document.savedBillsForm.cmbSearch.value+'&updStatusFlag=<%=request.getParameter("updStatusFlag")%>';

		//document.savedBillsForm.displ.value=str;			
		document.savedBillsForm.method='post';
		document.savedBillsForm.submit();
	}
   
	function EnterkeyPressed(e,form)
	{	
	  
	  var whichCode = (window.Event) ? e.which : e.keyCode;
		if ((e.keyCode==13))
		{
	     searching();
		}
	}   


	   
   </script>
</head>
      
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"> </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set> 	                                                             
<hdiits:form name="savedBillsForm" validate="true" method="post"  >
	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
			
	<table border="0" cellspacing="0" cellpadding="0" align="center" width="100%" >
		<tr class="TableHeaderBG">
			<td  align="center" >
				<b> <c:if test="${(param['billStatus'] != null && param['billStatus'] == 'saved') 
						|| (resValue.StrCurrBillStatus != null && resValue.StrCurrBillStatus == 'saved')}">
				
		  			  <fmt:message key="SVBILLCMN.VIEWBILLHEADER.SAVED" bundle="${onlinebillcmnLabels}"></fmt:message>
		  			  </c:if> 
		  			<c:if test="${(param['billStatus'] != null && param['billStatus'] == 'BSNT_TO') 
		  					|| (resValue.StrCurrBillStatus != null && resValue.StrCurrBillStatus == 'BSNT_TO')}">
		  					<fmt:message key="SVBILLCMN.VIEWBILLHEADER.SENDTOTO" bundle="${onlinebillcmnLabels}"></fmt:message>
		  				</c:if> 
		  			<c:if test="${(param['billStatus'] != null && param['billStatus'] == 'BRJCT_AUD') 
		  					|| (resValue.StrCurrBillStatus != null && resValue.StrCurrBillStatus == 'BRJCT_AUD')}">
								    <fmt:message key="SVBILLCMN.VIEWBILLHEADER.REJECTEDBYTO" bundle="${onlinebillcmnLabels}"></fmt:message>
					</c:if> 
				</b>				
			</td>
		</tr>
		
		<tr> 
			<td  align="center">&nbsp;</td>
		</tr>
		
		<tr>
			<table align="center" width="90%" align=left>
				<tr>
					<td width="53%">
					</td>
					
					<td width="8%" align="left">
						<b><fmt:message key="SVBILLCMN.SEARCH" bundle="${onlinebillcmnLabels}"></fmt:message></b>
					</td>
					<td width="*">
						<select  onKeyPress= "return EnterkeyPressed(event,document.forms[0])"  name="cmbSearch" id="id_cmbSearch" onchange="showDtPic()" onfocus="showDtPic()">
							<option value="0"><fmt:message key="SVBILLCMN.SELECT" bundle="${onlinebillcmnLabels}"></fmt:message></option>
								
							<option value="BR.billCntrlNo" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.billCntrlNo")) { out.print("selected"); }  %> selected="true">
								<fmt:message key="CMN.BILLCNTRLNO" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="BR.billDate" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.billDate")) { out.print("selected"); }  %>>
								<fmt:message key="SVBILLCMN.BILLDATE" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="BT.subjectDesc" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BT.subjectDesc")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILLTYPE" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="BR.tcBill" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.tcBill")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.BILLCATEGORY" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="BR.budmjrHd" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.budmjrHd")) { out.print("selected"); }  %>>
								<fmt:message key="CMN.MJRHD" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="BR.billGrossAmount" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.billGrossAmount")) { out.print("selected"); }  %> >
								<fmt:message key="SVBILLCMN.BILLAMOUNT" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="DD.ddoNo" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("DD.ddoNo")) { out.print("selected"); }  %> >
								<fmt:message key="SVBILLCMN.DDONO" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<option value="DD.ddoName" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("DD.ddoName")) { out.print("selected"); }  %>>
								<fmt:message key="SVBILLCMN.DDONAME" bundle="${onlinebillcmnLabels}"></fmt:message>
							</option>
							
							<c:choose>
							   	<c:when test="${resValue.StrCurrBillStatus == 'saved'}">
									<option value="BR.currBillStatus" <% if(request.getParameter("cmbSearch")!= null && request.getParameter("cmbSearch").equals("BR.currBillStatus")) { out.print("selected"); }  %>>
										<fmt:message key="SVBILLCMN.BILLSTATUS" bundle="${onlinebillcmnLabels}"></fmt:message>
									</option>
								</c:when>
					   	 	</c:choose>
						</select>
					</td>
					<td >
						<input id="txtSearch" type="text" onKeyPress= "return EnterkeyPressed(event,document.forms[0])"  name="txtSearch" size="15" value="<% if(request.getParameter("txtSearch")!= null) { out.print(request.getParameter("txtSearch")); }  %>" onkeyup="checkCategorySelection()" />
						<div id="dtpicker" style="display:none">
						
						<img src=images/CalendarImages/ico-calendar.gif   width=20 onClick=window_open("txtSearch",375,570) ></div>                       						
						</td>
					<td width="2%">
						<a href="#" onclick="searching();">
					<!-- commented by Ankit Bhatt for displaying Search Image -->
						<%--
						<img src="common/images/search.gif" align="right" height="16" width="16"  /></a>
						--%>
						<img src="images/payroll/search_icon.gif" align="right" height="16" width="16"  /></a>
					</td>
				</tr>
			</table>	
		</tr>

		<tr> 
			<td  align="center">&nbsp;</td>
		</tr>
	</table>
	  	
	<display:table list="${resValue.MySavedBills}" pagesize="20" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate" id="VO" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		<display:column  headerClass="datatableheader" >
			<input name="chkbox" value="${VO[0]}" type="checkbox" align="middle" >
		</display:column>	
		
		<display:column class="oddcentre"  titleKey="CMN.BILLCNTRLNO" sortable="true" headerClass="datatableheader" style="width:14%">
			<center><a href="javascript:void(0)" onclick="javascript:showSavedBill('${VO[0]}','${VO[9]}','${VO[18]}')">
				<c:out value="${VO[1]}"/>
				<input type="hidden" name="billCntrlNo" id="billCntrlNo" value="${VO[1]}"/>
				<input type="hidden" name="newBillNo" id="newBillNo" value="${VO[14]}"/>
				<input type="hidden" name="newBillCntrlNo" id="newBillCntrlNo" value="${VO[15]}"/>
				<input type="hidden" name="SelBillStatus" id="SelBillStatus" value="${VO[9]}"/> 
				<input type="hidden" name="newBillStatus" id="newBillStatus" value="${VO[16]}"/> 
			</a>
			</center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey="SVBILLCMN.BILLDATE" sortable="true" headerClass="datatableheader" style="width:10%" >	
			<center><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${VO[2]}"/></center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey="CMN.BILLTYPE" sortable="true" headerClass="datatableheader" style="width:12%" >
			<center><c:out value="${VO[4]}"/></center>
		</display:column>									
		
		<display:column  class="oddcentre" titleKey="CMN.BILLCATEGORY" sortable="true" headerClass="datatableheader" style="width:10%" >
			<center><c:out value="${VO[17]}"/></center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey="CMN.MJRHD" sortable="true" headerClass="datatableheader"  style="width:12%">				
			<center><c:out value="${VO[5]}"/></center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey="CMN.TOKENNO"  sortable="true" headerClass="datatableheader"  style="text-align: center;">				
			<center><c:out value="${VO[6]}"/></center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey="CMN.TOKENDT" sortable="true" headerClass="datatableheader"  style="text-align: center;">				
			<center><fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${VO[7]}"/></center>
		</display:column>

		<display:column  class="oddcentre" titleKey="SVBILLCMN.BILLAMOUNT" sortable="true" headerClass="datatableheader" style="width:10%" >
			<center><c:out value="${VO[8]}"/></center>
		</display:column>
					
		<display:column  class="oddcentre" titleKey='SVBILLCMN.DDONO' sortable="true" headerClass="datatableheader" style="width:10%">
		    <center><c:out value="${VO[12]}"/></center>
		</display:column>
		
		<display:column  class="oddcentre" titleKey='SVBILLCMN.DDONAME' sortable="true" headerClass="datatableheader" style="width:14%">
			<c:out value="${VO[13]}"/>
		</display:column>
				    
		<c:choose>
		    <c:when test="${resValue.StrCurrBillStatus == 'saved'}">
			    <display:column  class="oddcentre" titleKey='SVBILLCMN.BILLSTATUS' sortable="true" headerClass="datatableheader" style="width:14%">
				    <center>
					    <c:choose>
						    <c:when test="${VO[9] == 'BCRTD'}">
							    <c:out value="Created"/>
						    </c:when>
					
						    <c:otherwise>
							    <c:out value="Approved"/>
						    </c:otherwise>
					    </c:choose>
					</center>
				</display:column>
			</c:when>
	    </c:choose>
		
	    <display:footer media="html">
		</display:footer>
	</display:table>
	
	<%
		ResultObject result=(ResultObject)request.getAttribute("result");
		Map resultMap=(Map)result.getResultValue();
		UserElements userActions = (UserElements)resultMap.get("homePageUserElements");
		
		long permission = 2;
		// permission is used for checking whether button is to be displayed or not.
		// permission = 1 is for showing and permission =2 is for hidden
		if(userActions!= null)
		{
		    UserElement userElementSntToTO_En = userActions.getUserElements(110019);
			UserElement userElementFrwd_En = userActions.getUserElements(110021);
			
			if(userElementSntToTO_En != null)
			{
				if(userElementSntToTO_En.getPermissionObject() != null)
				{	
				    //assuming that permission will be same for all languages
					permission = userElementSntToTO_En.getPermissionObject().getPermission();
					request.setAttribute("permission",permission);
				}
			}
			
			if(userElementFrwd_En != null)
			{
				if(userElementFrwd_En.getPermissionObject() != null)
				{	
				    //assuming that permission will be same for all languages
					permission = userElementFrwd_En.getPermissionObject().getPermission();
					request.setAttribute("permissionFrwd",permission);
				}
			}
		}
	%>
	  	
	<table border="0" cellspacing="0" cellpadding="0" align="center" width="100%">
		<tr>
			<td align="center" colspan="10">
				<c:if test="${permissionFrwd == 1}">
					<hdiits:button name="btnFrwrd" type="button" captionid="SVBILLCMN.BUTTON.FORWARD" bundle="${onlinebillcmnLabels}" onclick="forwardBill()" />					
				</c:if>
				<c:choose>
				<c:when test="${(param['billStatus'] != null && param['billStatus'] == 'BSNT_TO') 
		  					|| (resValue.StrCurrBillStatus != null && resValue.StrCurrBillStatus == 'BSNT_TO')}"></c:when>
		  		<c:when test="${(param['billStatus'] != null && param['billStatus'] == 'BRJCT_AUD') 
		  					|| (resValue.StrCurrBillStatus != null && resValue.StrCurrBillStatus == 'BRJCT_AUD')}">
  					<center>
						<hdiits:button type="button" name="btnCreateBill" captionid="SVBILLCMN.BUTTON.NEWFRMRJCTD" bundle="${onlinebillcmnLabels}" onclick="createNewBill()"/>
					</center>
		  		</c:when>
		  		<c:otherwise>
				<c:if test="${permission == 1}">				
					<hdiits:button type="button" name="btnSendTO" captionid="SVBILLCMN.BUTTON.SENDTOTO" bundle="${onlinebillcmnLabels}" onclick="sendtoTrsryOffice()"/>
				</c:if>				
				
				<hdiits:button type="button" name="btnDiscard" captionid="SVBILLCMN.BUTTON.DISCARD" bundle="${onlinebillcmnLabels}" onclick="discardBill()"/>
				
				</c:otherwise>
				</c:choose>
   			</td>
		</tr>
	</table>
	  	
	<!--  Added by Keyur for Workflow - Starts -->
	<hdiits:hidden name="toPost" id="toPost" default="-1"/>
	<hdiits:hidden name="toUser" id="toUser" default="-1"/> 
	<hdiits:hidden name="seltdTrsry" id="seltdTrsry" default="-1"/>
	<hdiits:hidden name="SendTo" default="-1" />
	<hdiits:hidden name="ToHeirarchyRefId" default="-1" />
	<hdiits:hidden name="CurrBillStatus" default="-1" />
	<hdiits:hidden name="hidBillNo" />
	<!--  Added by Keyur for Workflow - Ends -->
	
	<!-- Added to get the URL to refresh the page on close of Bills Page -->
	<hdiits:hidden name="parentUrl1" />
   <script type="text/javascript">
     //initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='') //message added for multiple bill discard issue by prasenjit
		{
			alert("${msg}");
		}	
     </script> 
</hdiits:form>     
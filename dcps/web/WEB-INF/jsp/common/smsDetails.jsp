
<%
try {
%>
<%@ taglib prefix	=	"c" 
			uri		=	"http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri		=	"http://displaytag.sf.net" 
		prefix		=	"display" %>
<%@ taglib prefix	=	"fmt" 
			uri		=	"http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix	=	"hdiits"
			 uri	=	"http://hdiits.tcs.com"%>
<%@ include file	=	"../core/include.jsp"%>

<fmt:setBundle basename	=	"resources.SMSLables"
					var	=	"smsLables"
				scope	=	"request" />
				
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lstSMSDetails" value="${resValue.lstSMS}" scope="request"></c:set>
				
<hdiits:form 	name	=	"smsDetails"
			 validate	=	"true"
			  method	=	"POST" >
			  
	<!-- body part of any page start-->
	<div id	=	"tabmenu">
	
	<ul		id	=	"maintab" 
		class	=	"shadetabs">
		
		<li class	=	"selected">
		
			<a href	=	"#"
				rel	=	"tcontent1">
				
				<hdiits:caption captionid	=	"SMS.TITLE" 
									bundle	=	"${smsLables}" />
			 </a>
		</li>
	</ul>
	</div>
	
	<div class	=	"tabcontentstyle">
	<!------------- Tab1 --------------------->
	<div 	id	=	"tcontent1"
		 class	=	"tabcontent"
		  tabno	=	"0">
		  
		  <!-- Code with Display Tag -->
		<display:table pagesize="20" name="${lstSMSDetails}" id="row" requestURI="" style="width:100%"  >
			<display:column class="tablecelltext"  titleKey="SMS.PHONENO" headerClass="datatableheader" >${row.originator}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.RECVDMSG" headerClass="datatableheader" >${row.message}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.RECVDMSGDATE" headerClass="datatableheader" >${row.messageDate}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.REPLYMSG" headerClass="datatableheader" >
			<c:choose>
			<c:when test="${empty row.outgoingSmsMessage}">
			Service is not available
			</c:when>
			<c:otherwise>
			---
			</c:otherwise>
			</c:choose>
		
			<c:forEach items="${row.outgoingSmsMessage}" var="outSMS" >
			${outSMS.message}
			</c:forEach>
			</display:column>
		</display:table>
		
		
	</div>
	
	<script type	=	"text/javascript">
	var navDisplay = false;
	</script>
	
	 <jsp:include page	=	"../core/tabnavigation.jsp">
		<jsp:param name	=	"disableReset"
				 value	=	"true" />
				 
		<jsp:param name	=	"disableSubmit"
				value	=	"${submitBtnDisabled}" />
				
		<jsp:param name	=	"submitText" 
				value	=	"${submitBtnValue}" />
				
		<jsp:param name	=	"closeText" 
				value	=	"${closeBtnValue}" />
				
		<jsp:param name	=	"closeURL" 
				value	=	"${closeUrl}" />
				
		<jsp:param name	=	"closeWindow" 
				value	=	"${closeWindow}" />
	</jsp:include>
	
	</div>
	
	<script type	=	"text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
</hdiits:form>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


<%
try {
%>
<%@ taglib prefix	=	"c" 
			uri		=	"http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix	=	"fmt" 
			uri		=	"http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri		=	"http://displaytag.sf.net" 
		prefix		=	"display" %>
<%@ taglib prefix	=	"hdiits"
			 uri	=	"http://hdiits.tcs.com"%>
<%@ include file	=	"../core/include.jsp"%>

<fmt:setBundle basename	=	"resources.SMSLables"
					var	=	"smsLables"
				scope	=	"request" />
				
<c:set var	=	"resultObj"
	 value	=	"${result}">
</c:set>

<c:set var	=	"resValue" 
	value	=	"${resultObj.resultValue}">
</c:set>

<c:set var	=	"lstSMSDetails" 
	value	=	"${resValue.lstSMS}" 
	scope	=	"request">
</c:set>
<c:out value	=	"${resValue.result}" ></c:out>
				
<hdiits:form 	name	=	"smsInterface"
			 validate	=	"true"
			  method	=	"POST" 
			  action	=	"ifms.htm?actionFlag=insertSMS">
			  
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
		  
	<table	class	=	tabtable
			 width	=	"100%">
		<tr>
			<td class	=	"fieldLabel"
				 width	=	"50%">
				
				<hdiits:caption captionid	=	"SMS.PHONENO"
									 bundle	=	"${smsLables}"
								captionfor	=	"originator" /> 
								
				<!-- TextBox to enter the Phone No. --> 				
				<hdiits:text  		name	=	"originator"
								 	id		=	"originatorId"
							 	maxlength	=	"10"
							 	mandatory	=	"true"
								 validation	=	"txt.isnumber"
									caption	=	"PhoneNo" />
			</td>
			<td class	=	"fieldLabel" 
				width	=	"50%">
				
				<hdiits:caption captionid	=	"SMS.MSG"
								 bundle		=	"${smsLables}"
								 captionfor	=	"message"  />
								 
				<!-- TextArea to enter the Message --> 					 
				<hdiits:textarea 	name	=	"message" 
								captionid	=	"SMS.MSG"
									 rows	=	"6"
									  cols	=	"40"
								maxlength	=	"100"
								mandatory	=	"true"
									bundle	=	"${smsLables}">
				</hdiits:textarea>
			</td>
		</tr>
		
	</table>
	
		<script type	=	"text/javascript">
	var navDisplay = false;
	</script>
	<script type="text/javascript">
	function loadPage()
	{
		document.forms[0].action='hdiits.htm?actionFlag=SmsInterFace';
		
		document.forms[0].submit();
	}
	</script>
	
	 <jsp:include page	=	"../core/tabnavigation.jsp">
		
		<jsp:param name	=	"submitText" 
				value	=	"Send" />
		
	</jsp:include>
		<script type	=	"text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	</script>
	<hdiits:button name="refresh" type="button"  value="Refresh" onclick="loadPage()"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	
		  
		  <!-- Code with Display Tag -->
		<display:table pagesize="20" name="${lstSMSDetails}" id="row" requestURI="" style="width:100%"  >
			<display:column class="tablecelltext"  titleKey="SMS.PHONENO" headerClass="datatableheader" >${row.originator}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.RECVDMSG" headerClass="datatableheader" >${row.message}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.RECVDMSGDATE" headerClass="datatableheader" >${row.messageDate}</display:column>
			<display:column class="tablecelltext"  titleKey="SMS.REPLYMSG" headerClass="datatableheader" >
			
			<c:forEach items="${row.outgoingSmsMessage}" var="outSMS" >
			${outSMS.message}
			</c:forEach>
			</display:column>
		</display:table>
		
		
	</div>
	
</div>	
</hdiits:form>
<!-- body part of any page end-->
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
